package com.jsp.ets.user;


import com.jsp.ets.exception.InvalidOtpException;
import com.jsp.ets.exception.InvalidStackValueException;
import com.jsp.ets.exception.RegistrationSessionExpiredException;
import com.jsp.ets.exception.UserNotFoundByIdException;
import com.jsp.ets.mapper.UserMapper;
import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingRepository;
import com.jsp.ets.security.RegistrationRequest;
import com.jsp.ets.utility.CacheHelper;
import com.jsp.ets.utility.MailSenderService;
import com.jsp.ets.utility.MessageModel;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@AllArgsConstructor
@Service
public class UserService {

	private UserRepository userRepo;
	private UserMapper userMapper;
	private RatingRepository ratingRepo;
	private MailSenderService mailSender;
	private Random random;
	private CacheHelper cacheHelper;

	public UserResponse registerUser(RegistrationRequest registrationRequest, UserRole role) throws MessagingException {
		User user = switch (role) {
		case ADMIN -> new Admin();
		case HR -> new HR();
		case STUDENT -> new Student();
		case TRAINER -> new Trainer();
		default -> throw new IllegalArgumentException("Unexpected value: " + role);
		};

		user = userMapper.mapToUserEntity(registrationRequest, user);
		user.setRole(role);
		Integer otp = random.nextInt(100000, 999999);
		cacheHelper.userCache(user);
		cacheHelper.otpCache(otp,user.getEmail());
		sendOtpToMailId(user.getEmail(),otp);
		return userMapper.mapToUserResponse(user);
	}

	private void sendOtpToMailId (String email, Integer otp) throws MessagingException {
		String text = "<!DOCTYPE html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
				"    <title>OTP Verification</title>\n" +
				"    <style>\n" +
				"        body {\n" +
				"            font-family: Arial, sans-serif;\n" +
				"            background-color: #f4f4f4;\n" +
				"            display: flex;\n" +
				"            justify-content: center;\n" +
				"            align-items: center;\n" +
				"            height: 100vh;\n" +
				"            margin: 0;\n" +
				"        }\n" +
				"        .container {\n" +
				"            background-color: #ffffff;\n" +
				"            padding: 20px;\n" +
				"            border-radius: 10px;\n" +
				"            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n" +
				"            max-width: 400px;\n" +
				"            text-align: center;\n" +
				"        }\n" +
				"        .container h2 {\n" +
				"            color: #333333;\n" +
				"        }\n" +
				"        .otp {\n" +
				"            font-size: 24px;\n" +
				"            color: #2d89ef;\n" +
				"            font-weight: bold;\n" +
				"            margin: 10px 0;\n" +
				"        }\n" +
				"        .note {\n" +
				"            font-size: 14px;\n" +
				"            color: #888888;\n" +
				"        }\n" +
				"        .highlight {\n" +
				"            color: #ff0000;\n" +
				"        }\n" +
				"    </style>\n" +
				"</head>\n" +
				"<body>\n" +
				"\n" +
				"    <div class=\"container\">\n" +
				"        <h2>OTP Verification</h2>\n" +
				"        <p>Dear User,</p>\n" +
				"        <p>Your OTP to verify your email is:</p>\n" +
				"        <div class=\"otp\">"+ otp + "</div>\n" +
				"        <p class=\"note\">Valid for the next <span class=\"highlight\">5 minutes</span> only.</p>\n" +
				"    </div>\n" +
				"\n" +
				"</body>\n" +
				"</html>\n";
		MessageModel messageModel = new MessageModel();
		messageModel.setTo(email);
		messageModel.setSubject("Verify your email for EDU-Tracking");
		messageModel.setSentDate(new Date());
		messageModel.setText(text);

		mailSender.sendMail(messageModel);
	}

	public UserResponse verifyUser(OtpRequest otpRequest) {
		Integer cachedOtp = cacheHelper.getCachedOtp(otpRequest.getEmail());

		if (!cachedOtp.equals(otpRequest.getOtp())) {
			throw new InvalidOtpException("Invalid Otp entered");
		}

		User cachedUser = cacheHelper.getRegisteringUser(otpRequest.getEmail());
		if(!cachedUser.getEmail().equals(otpRequest.getEmail()))
			throw new RegistrationSessionExpiredException("Registration session is expired");

		User user = userRepo.save(cachedUser);
		return userMapper.mapToUserResponse(user);
	}

	public UserResponse updateUser(UserRequest userRequest, String userId) {
		return userRepo.findById(userId)
				.map(user -> {
					switch (user.getRole()) {
					case STUDENT -> {
						Student student = userMapper.mapToStudentEntity((StudentRequest) userRequest, (Student) user);
						return userMapper.mapToStudentResponse(userRepo.save(student));
					}
					case TRAINER -> {
						Trainer trainer = userMapper.mapToTrainerEntity((TrainerRequest) userRequest, (Trainer) user);
						return userMapper.mapToTrainerResponse(userRepo.save(trainer));
					}
					default -> throw new IllegalArgumentException("Unexpected value: " + user.getRole());
					}
				})
				.orElseThrow(() -> new UserNotFoundByIdException("Failed to update User"));

	}

	public StudentResponse updateStudentStack(String userId, String stack) {
		Student student = (Student) userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("Failed to update Stack"));
		
		Stack techStack = null;
		try {
			techStack = Stack.valueOf(stack);
		} catch(RuntimeException ex) {
			throw new InvalidStackValueException("Invalid stack value provided");
		}
		
		techStack.getSubjects().forEach(subject -> {
			Rating rating = new Rating();
			rating.setStudent(student);
			rating.setSubject(subject);
			ratingRepo.save(rating);
		});

		student.setStack(techStack);
		userRepo.save(student);
		return userMapper.mapToStudentResponse(student);
	}

}
