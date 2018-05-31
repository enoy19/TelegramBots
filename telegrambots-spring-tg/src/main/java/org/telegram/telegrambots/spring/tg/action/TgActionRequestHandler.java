package org.telegram.telegrambots.spring.tg.action;

import org.telegram.telegrambots.spring.tg.action.request.TgParameterType;
import org.telegram.telegrambots.spring.tg.action.request.TgParameterType.NonMatchingTypeException;
import org.telegram.telegrambots.spring.tg.action.request.TgRequestHandlerHopsComposition;
import org.telegram.telegrambots.spring.tg.action.request.TgRequestResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.api.objects.Message;

import java.lang.reflect.Method;
import java.util.List;

/**
 * contains data of {@link org.telegram.telegrambots.spring.tg.action.request.TgRequest}s in {@link TgController}s ({@link TgAction})
 *
 * @author enoy19
 * @see TgAction
 * @see TgActionRegisterer
 */
@Getter
@Setter
@RequiredArgsConstructor
public final class TgActionRequestHandler {

	private final String name;
	private final Method method;
	private final TgParameterType[] parameters;

	public TgRequestResult execute(Object instance, List<Message> messages) {

		Object[] data = new Object[parameters.length];
		for (int i = 0; i < messages.size(); i++) {
			Message message = messages.get(i);
			TgParameterType parameterType = parameters[i];
			Object datum = parameterType.getData(message);
			data[i] = datum;
		}

		try {
			Object result = method.invoke(instance, data);

			if (result instanceof TgRequestResult) {
				return (TgRequestResult) result;
			}
		} catch (ReflectiveOperationException e) {
			throw new TgActionRequestHandlerException(e);
		}

		return TgRequestResult.OK;
	}

	public TgRequestHandlerHopsComposition getHops(List<Message> messages) throws NonMatchingTypeException {
		int hopsOverall = 0;

		for (int i = 0; i < messages.size(); i++) {
			hopsOverall += TgParameterType.getHops(parameters[i], messages.get(i));
		}

		return new TgRequestHandlerHopsComposition(this, hopsOverall);
	}

	public int getParameterCount() {
		return parameters.length;
	}

}
