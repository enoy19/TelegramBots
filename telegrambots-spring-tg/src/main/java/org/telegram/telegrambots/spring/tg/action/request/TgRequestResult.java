package org.telegram.telegrambots.spring.tg.action.request;

/**
 * May be returned in any {@link org.telegram.telegrambots.spring.tg.action.TgController} class method annotated with {@link TgRequest} to gain more
 * control over your actions.
 * @author enoy19
 * @see TgRequest
 */
public enum TgRequestResult {
	OK, ABORT, RETRY
}
