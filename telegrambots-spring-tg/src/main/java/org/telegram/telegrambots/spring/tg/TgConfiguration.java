package org.telegram.telegrambots.spring.tg;

import org.telegram.telegrambots.spring.tg.bot.TgBotMessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * Spring configuration that loads all relevant spring-tg components
 *
 * @author enoy19
 * @see EnableTgBot
 */
@Configuration
@ComponentScan(value = "org.telegram.telegrambots.spring.tg")
@RequiredArgsConstructor
public class TgConfiguration {

	private final TgBotMessageHandler messageHandler;

	@Bean
	@ConditionalOnMissingBean(TelegramBotsApi.class)
	public TelegramBotsApi telegramBotsApi() {
		return new TelegramBotsApi();
	}

}
