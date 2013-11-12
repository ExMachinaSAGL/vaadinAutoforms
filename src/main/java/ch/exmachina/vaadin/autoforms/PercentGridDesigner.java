package ch.exmachina.vaadin.autoforms;

import java.util.LinkedList;

/**
 * The class complete the form fields adding information on widths based on fields on the same row
 * @autor Marco Manzi
 */
class PercentGridDesigner {

	/**
	 *
	 * @param components
	 * @return
	 */
	public LinkedList<LinkedList<FormComponent>> addPercents(LinkedList<LinkedList<FormComponent>> components) {
		LinkedList<LinkedList<FormComponent>> toDesign = components;
		for (LinkedList<FormComponent> rowComponents: toDesign) {
			addPercentOnRow(rowComponents);
		}
		return components;
	}


	private void addPercentOnRow(LinkedList<FormComponent> rowComponents) {
		int numberOfComponentsWithoutInfo = evaluateComponentsWithoutInfoFrom(rowComponents);
		int percentAlreadyBusyFromOtherComponents = evaluateComponentsTotalPercentFrom(rowComponents);
		if (percentAlreadyBusyFromOtherComponents > 100) throw new IllegalStateException("All Members of a row can have a total width of 100");
		for (FormComponent formComponent: rowComponents) {
			int percentForEachComponent = evaluatePercentForEachComponent(percentAlreadyBusyFromOtherComponents,
					numberOfComponentsWithoutInfo);
			if (formComponent.hasAutomaticWidth()) {
				formComponent.setWidthPercent(percentForEachComponent);
			}
		}
	}

	private int evaluatePercentForEachComponent(int percentAlreadyBusy, int numberOfAutomaticComponent) {
		if (numberOfAutomaticComponent > 0) {
			return (100 - percentAlreadyBusy) / numberOfAutomaticComponent;
		}
		return 0;
	}

	private int evaluateComponentsTotalPercentFrom(LinkedList<FormComponent> rowComponents) {
		int result = 0;
		for (FormComponent formComponent: rowComponents) {
			if (!formComponent.hasAutomaticWidth()) {
				result += formComponent.getWidthPercent() + formComponent.getMarginLeftPercent();
			}
		}
		return result;
	}

	private int evaluateComponentsWithoutInfoFrom(LinkedList<FormComponent> rowComponents) {
		int result = 0;
		for (FormComponent formComponent: rowComponents) {
			if (formComponent.hasAutomaticWidth())
				result++;
		}
		return result;
	}

}