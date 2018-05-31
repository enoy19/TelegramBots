package org.telegram.telegrambots.spring.tg;

import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.starter.EnableTelegramBots;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this thing enables all you need to get your spring telegram bots up and running. You just need to put it over any configuration.
 * Make sure, that you've provided the following two properties and you're good to go:
 * <ul><li>bots.token (the token of the bots that you get from the @BotFather in Telegram)</li><li>bots.name (the name you've given your bots)</li></ul><br>
 * it imports the {@link TgConfiguration} configuration. Alternatively you could just {@link Import} the {@link TgConfiguration} yourself
 * @author enoy19
 * @see TgConfiguration
 */
@EnableTelegramBots
@Import(TgConfiguration.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableTgBot {
}
