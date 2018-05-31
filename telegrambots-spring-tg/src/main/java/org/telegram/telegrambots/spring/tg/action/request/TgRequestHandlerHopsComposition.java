package org.telegram.telegrambots.spring.tg.action.request;

import org.telegram.telegrambots.spring.tg.action.TgActionRequestHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.api.objects.Message;

import javax.validation.constraints.NotNull;

/**
 * A model class to group {@link TgActionRequestHandler} and hop count
 * @author enoy19
 * @see TgParameterType
 * @see TgParameterType#getHops(Class, int)
 * @see TgParameterType#getHops(TgParameterType, Message)
 * @see org.telegram.telegrambots.spring.tg.bot.TgMessageDispatcher
 */
@Getter
@RequiredArgsConstructor
public class TgRequestHandlerHopsComposition implements Comparable<TgRequestHandlerHopsComposition> {

	private final TgActionRequestHandler handler;
	private final int hops;

	@Override
	public int compareTo(TgRequestHandlerHopsComposition o) {
		return Integer.compare(this.hops, o.hops);
	}
}
