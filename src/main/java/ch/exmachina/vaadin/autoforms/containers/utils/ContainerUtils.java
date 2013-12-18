package ch.exmachina.vaadin.autoforms.containers.utils;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractSelect;

/**
 * @autor Marco Manzi
 */
public class ContainerUtils {
	public static <T> void setOnSelectContainerBeanValue(T bean, String fieldName, AbstractField<?> field) {
		if (field instanceof AbstractSelect) {
			AbstractSelect select = ((AbstractSelect) field);
			if (select.getContainerDataSource().getItemIds().size() == 0) {
				String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Object currentValue = bean.getClass().getMethod(getterName).invoke(bean);
					if (currentValue != null) select.getContainerDataSource().addItem(currentValue);
				} catch (Exception e) {
					throw new RuntimeException("Bean doens't have the getter:" + getterName);
				}
			}

		}
	}
}
