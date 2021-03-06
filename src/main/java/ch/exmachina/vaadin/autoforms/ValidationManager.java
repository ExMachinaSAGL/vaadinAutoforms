package ch.exmachina.vaadin.autoforms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.*;
import com.vaadin.event.FieldEvents.*;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import org.apache.commons.lang3.*;

public class ValidationManager {

	public static final String KEY_REQUIRED_ERROR = ValidationManager.class.getSimpleName() + ".requiredErrorMessage";

	private String errorMessage;

	public void addRequiredValidationAndValidateOnBlur(FieldGroup fieldGroup) {
		fieldGroup.addCommitHandler(new CommitHandler() {
			@Override
			public void preCommit(CommitEvent commitEvent) throws CommitException {
				for (Field<?> field : commitEvent.getFieldBinder().getFields()) {
					setRequiredError(field);
				}
			}

			@Override
			public void postCommit(CommitEvent commitEvent) throws CommitException {
			}
		});

		for (Field<?> field : fieldGroup.getFields()) {
			addValidateOnBlur(field);
		}
	}

	private void addValidateOnBlur(final Field<?> field) {
		((BlurNotifier) field).addBlurListener(new BlurListener() {
			@Override
			public void blur(BlurEvent event) {
				setRequiredError(field);
				field.validate();
			}
		});
	}

	private void setRequiredError(Field<?> field) {
		if (field.isRequired()) {
			Object value = field.getValue();
			boolean nullOrBlank = value == null || (value.getClass().isAssignableFrom(String.class) && StringUtils.isBlank((String) value));
			((AbstractComponent) field).setComponentError(nullOrBlank ? new UserError(getErrorMessage()) : null);
		}
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
