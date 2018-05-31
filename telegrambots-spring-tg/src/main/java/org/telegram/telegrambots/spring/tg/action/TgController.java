package org.telegram.telegrambots.spring.tg.action;

import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.api.objects.Message;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * used to define a bean as Telegram Controller which basically behaves like a command entry point.
 * Must be a singleton to be registered
 * @author enoy19
 * @see TgActionRegisterer
 * @see TgAction
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Controller
public @interface TgController {

	String name();

	String description();

	/**
	 * Bots that match any of the given bot names will execute the {@link TgController}
	 * if left empty, every bots will execute this. The bot name is the bean name.
	 * You can register bots manually by using {@link org.telegram.telegrambots.spring.tg.bot.TgBotRegistry}
	 */
	String[] bots() default {};

	/**
     * @return the class of the validator of this method. If a command validator and regex is present both must match!
	 * @see CommandValidator
	 */
	Class<? extends CommandValidator> commandValidator() default NoCommandValidator.class;

	/**
	 * the command is valid if the {@link TgController#commandValidator()} has {@link NoCommandValidator} the {@link org.telegram.telegrambots.spring.tg.bot.TgMessageDispatcher},t
	 * he given command has text ({@link Message#hasText()}) and the regex matches
     * @return if the given message text matches this regex. This controller is a valid candidate. If a command validator and regex is present both must match!
	 */
	String regex() default "";

}
