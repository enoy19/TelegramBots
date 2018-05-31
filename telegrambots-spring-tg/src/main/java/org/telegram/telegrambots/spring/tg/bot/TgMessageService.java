package org.telegram.telegrambots.spring.tg.bot;

import org.telegram.telegrambots.spring.tg.scope.context.TgContext;
import org.telegram.telegrambots.spring.tg.scope.context.TgContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.Message;

import java.util.List;

/**
 * Telegram message service. Sends messages to the current {@link TgContext} user
 *
 * @author enoy19
 */
@Service
@Scope("tg")
@RequiredArgsConstructor
public class TgMessageService {

    private final TgMessageServiceContextless messageServiceContextless;

    /**
     * @see TgMessageServiceContextless#sendMessage(long, String)
     */
    public Message sendMessage(String message) {
        return messageServiceContextless.sendMessage(getCurrentChatId(), message);
    }

    /**
     * @see TgMessageServiceContextless#editMessage(String, Message)
     */
    public void editMessage(String messageText, Message message) {
        messageServiceContextless.editMessage(messageText, message);
    }

    /**
     * @see TgMessageServiceContextless#sendReplyKeyboard(long, String, List, boolean, int)
     */
    public Message sendReplyKeyboard(String message, List<String> keyboardData, boolean oneTimeKeyboard, int maxColumns) {
        return messageServiceContextless.sendReplyKeyboard(getCurrentChatId(), message, keyboardData, oneTimeKeyboard, maxColumns);
    }

    /**
     * @see TgMessageServiceContextless#sendReplyKeyboard(long, String, List, boolean)
     */
    public Message sendReplyKeyboard(String message, List<List<String>> keyboardText, boolean oneTimeKeyboard) {
        return messageServiceContextless.sendReplyKeyboard(getCurrentChatId(), message, keyboardText, oneTimeKeyboard);
    }

    /**
     * @see TgMessageServiceContextless#sendReplyKeyboard(long, String, String[][], boolean)
     */
    public Message sendReplyKeyboard(String message, String[][] keyboardText, boolean oneTimeKeyboard) {
        return messageServiceContextless.sendReplyKeyboard(getCurrentChatId(), message, keyboardText, oneTimeKeyboard);
    }

    /**
     * @see TgMessageServiceContextless#removeKeyboard(long)
     */
    public void removeKeyboard() {
        messageServiceContextless.removeKeyboard(getCurrentChatId());
    }

    private long getCurrentChatId() {
        return TgContextHolder.currentContext().getChatId();
    }


}
