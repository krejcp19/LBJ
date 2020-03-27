package testutils;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import gui.components.LBJValueHolderComponent;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.generate.GenerateForm;
import gui.forms.removenotnullconstraint.RemoveNotNullConstraintForm;

public class LBJTestUtils {

	private LBJTestUtils() {
		// only static methods
	}

	public static void focus(LBJValueHolderComponent<?> component) {
		if (!(component.getComponent() instanceof Interactable)) {
			throw new IllegalArgumentException("I can only focus interactable components!");
		}
		// ((Interactable) component.getComponent()).onEnterFocus(null, null);
		((Interactable) component.getComponent()).takeFocus();
	}

	/**
	 * Hits enter on component
	 */
	public static void click(LBJValueHolderComponent<?> component) {
		if (!(component.getComponent() instanceof Interactable)) {
			throw new IllegalArgumentException("I can only click on interactable components!");
		}
		click((Interactable) component.getComponent());
	}

	/**
	 * Hits enter on component
	 */
	public static void click(Interactable interactable) {
		interactable.handleInput(new KeyStroke(KeyType.Enter));
	}

	/**
	 * First it will focus the component then it will set value of component and
	 * updates form to simulate actual user input.
	 */
	public static void setValueOf(LBJValueHolderComponent<?> component, Object value) {
		if (component == null || component.getForm() == null || value == null) {
			throw new IllegalArgumentException("I aint working with null values");
		}
		if (component.forType() != value.getClass()) {
			throw new IllegalArgumentException("Invalid combination of component and value! Type of component: "
					+ component.forType().getSimpleName() + " but type of passed value: "
					+ value.getClass().getSimpleName());
		}
		focus(component);
		component.setRawValue(value);
		component.getForm().update();
	}

	/**
	 * @return {@link CreateTableForm} that is already focused
	 */
	public static CreateTableForm getCreateTableForm() {
		CreateTableForm form = new CreateTableForm(new BasicWindow(), new LBJMockForm());
		form.focus();
		return form;
	}

	/**
	 * @return {@link AddColumnForm} that is already focused
	 */
	public static AddColumnForm getAddColumnForm() {
		AddColumnForm form = new AddColumnForm(new BasicWindow(), new LBJMockForm());
		form.focus();
		return form;
	}

	/**
	 * @return {@link RemoveNotNullConstraintForm} that is already focused
	 */
	public static RemoveNotNullConstraintForm getRemoveNotNullConstraintForm() {
		RemoveNotNullConstraintForm form = new RemoveNotNullConstraintForm(new BasicWindow(), new LBJMockForm());
		form.focus();
		return form;
	}

	/**
	 * @return {@link GenerateForm} that is already focused
	 */
	public static GenerateForm getGenerateForm() {
		GenerateForm form = new GenerateForm(new BasicWindow(), new LBJMockForm());
		form.focus();
		return form;
	}

}
