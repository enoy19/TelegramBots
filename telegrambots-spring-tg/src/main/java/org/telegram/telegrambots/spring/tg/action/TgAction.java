package org.telegram.telegrambots.spring.tg.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * a class that unites the data of {@link TgController} and {@link org.telegram.telegrambots.spring.tg.action.request.TgRequest}
 * and provides some convenient methods.
 *
 * @author enoy19
 * @see TgController
 * @see CommandValidator
 * @see TgActionRequestHandler
 */
@Getter
@RequiredArgsConstructor
public final class TgAction {

	private final Class<?> controllerClass;
	private final String name;
	private final String description;
	private final String[] bots;
	private final String regex;
	private final Class<? extends CommandValidator> commandValidatorClass;
	private final List<TgActionRequestHandler> requestHandlers;
	private final TgActionRequestHandler preAction;
	private final TgActionRequestHandler postAction;

	public boolean hasRegex() {
		return Objects.nonNull(regex) && !regex.trim().isEmpty();
	}

	public boolean isCommandValidatorExisting() {
		Class<? extends CommandValidator> commandValidatorClass = getCommandValidatorClass();
		return !commandValidatorClass.equals(NoCommandValidator.class);
	}

	public boolean isRegexMatching(String text) {
		return text.matches(regex);
	}

	public boolean isRegexExisting() {
		return Objects.nonNull(regex) && !regex.trim().isEmpty();
	}

	public boolean isBotsExisting() {
		return bots.length > 0;
	}

}
