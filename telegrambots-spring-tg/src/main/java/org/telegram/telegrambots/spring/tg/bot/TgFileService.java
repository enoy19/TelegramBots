package org.telegram.telegrambots.spring.tg.bot;

import org.telegram.telegrambots.spring.tg.scope.context.TgContext;
import org.telegram.telegrambots.spring.tg.scope.context.TgContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.AbsSender;

import java.io.File;

/**
 * @see TgFileServiceContextless
 */
@Service
@Scope("tg")
@RequiredArgsConstructor
public class TgFileService {

	private final TgFileServiceContextless fileServiceContextless;
	private final TgBotRegistry botRegistry;

	public File download(Object object) {
		final AbsSender absSender = getTgContextAbsSender();
		final String botToken = getBotToken(absSender);
		return fileServiceContextless.download(absSender, botToken, object);
	}

	public String getFileUrl(String fileId) {
		final AbsSender absSender = getTgContextAbsSender();
		final String botToken = getBotToken(absSender);
		return fileServiceContextless.getFileUrl(absSender, botToken, fileId);
	}

	public File download(Object object, String suffix) {
		final AbsSender absSender = getTgContextAbsSender();
		final String botToken = getBotToken(absSender);
		return fileServiceContextless.download(absSender, botToken, object, suffix);
	}

	private AbsSender getTgContextAbsSender() {
		final TgContext tgContext = TgContextHolder.currentContext();
		return tgContext.getAbsSender();
	}

	private String getBotToken(AbsSender absSender) {
		return botRegistry.getBotToken(absSender);
	}

}
