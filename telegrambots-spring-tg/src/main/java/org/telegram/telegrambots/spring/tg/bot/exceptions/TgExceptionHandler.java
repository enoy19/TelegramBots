package org.telegram.telegrambots.spring.tg.bot.exceptions;

public interface TgExceptionHandler {

	boolean accepts(Throwable e);

	void handle(Throwable e);

}
