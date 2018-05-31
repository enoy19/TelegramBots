package org.telegram.telegrambots.spring.tg.scope.context;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

/**
 * The Telegram context. It holds {@link User} information.
 *
 * @author enoy19
 * @see TgContextHolder
 * @see User
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"userId", "chatId", "absSenderName"})
public class TgContext {

	private AbsSender absSender;
	private String absSenderName;
	private User user;
	private Chat chat;
	private long userId;
	private long chatId;

	public void setUser(User user) {
		this.user = user;
		this.userId = user.getId();
	}

	public void setChat(Chat chat) {
		this.chat = chat;
		this.chatId = chat.getId();
	}

}
