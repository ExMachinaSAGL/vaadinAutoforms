package ch.exmachina.vaadin.autoforms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.*;
import com.vaadin.event.FieldEvents.*;
import com.vaadin.server.*;
import com.vaadin.ui.*;

public class ValidationManager {

	//private static final String KEY_REQUIRED_ERROR = ValidationManager.class.getSimpleName() + ".requiredErrorMessage";

	private ErrorMessage requiredError = new UserError("XXX");

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
			((AbstractComponent) field).setComponentError(field.getValue() == null ? requiredError : null);
		}
	}
}
