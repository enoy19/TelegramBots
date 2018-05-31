package org.telegram.telegrambots.spring.tg.action;

import org.telegram.telegrambots.api.objects.Message;

/**
 * A useless {@link CommandValidator} that is just ignored by the {@link org.telegram.telegrambots.spring.tg.bot.TgMessageDispatcher}
 * when specified in {@link TgController} (which it is by default)
 *
 * @author enoy19
 * @see CommandValidator
 * @see TgController
 * @see TgController#commandValidator()
 * @see org.telegram.telegrambots.spring.tg.bot.TgMessageDispatcher
 */
public final class NoCommandValidator implements CommandValidator {

	private NoCommandValidator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean validate(Message message) {
		throw new UnsupportedOperationException();
	}

}
