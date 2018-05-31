package org.telegram.telegrambots.spring.tg.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Registers the {@link TgScope} in the {@link org.springframework.beans.factory.BeanFactory}
 * @author enoy19
 * @see TgScope
 */
@Component
public class TgScopeRegisterer implements BeanFactoryPostProcessor {

	/**
	 * simply registers {@link TgScope} with the name "tg"
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
		factory.registerScope("tg", new TgScope());
	}

}
