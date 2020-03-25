package gui.forms;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;

import gui.components.LBJComponent;
import gui.components.LBJValueHolderComponent;
import gui.updaters.LBJFormUpdater;
import gui.utils.LBJFormUtils;
import gui.validators.LBJFormValidator;

public abstract class LBJForm implements Runnable {

	private Window window;
	// AKA focused
	private boolean visible;
	private boolean initialized;
	private Panel content;
	private List<LBJComponent> components;
	private List<LBJFormUpdater<LBJForm>> updaters;
	private List<LBJFormValidator<LBJForm>> validators;

	public LBJForm(Window window) {
		this.window = window;
	}

	@Override
	public abstract String toString();

	public void initialize() {
		initializeContent();
		initializeComponents();
		addFormUpdaters();
		addFormValidators();
		addComponentsToContent();
		addButtonsToContent();
		initialized = true;
	}

	public abstract void initializeComponents();

	public void initializeContent() {
		setContent(LBJFormUtils.initializeDefaultContent());
	}

	public abstract void addFormUpdaters();

	public abstract void addFormValidators();

	public abstract void addComponentsToContent();

	public abstract void addButtonsToContent();

	@Override
	public void run() {
		focus();
	}

	public void update() {
		if (!isVisible()) {
			return;
		}
		for (LBJFormUpdater<LBJForm> updater : getUpdaters()) {
			updater.update(this);
		}
		for (LBJComponent component : getComponents()) {
			if (component instanceof LBJValueHolderComponent<?> && component.isEnabled()) {
				((LBJValueHolderComponent<?>) component).update();
			}
		}
	}

	public boolean validate() {
		if (!isVisible() || !isInitialized()) {
			return true;
		}
		// one last update before validating
		update();
		boolean isFormValid = true;
		for (LBJFormValidator<LBJForm> validator : getValidators()) {
			isFormValid = validator.isValid(this) && isFormValid;
		}
		for (LBJComponent component : getComponents()) {
			if (component instanceof LBJValueHolderComponent<?> && component.isEnabled()) {
				isFormValid = ((LBJValueHolderComponent<?>) component).isValid() && isFormValid;
			}
		}
		return isFormValid;
	}

	public void focus() {
		visible = true;
		window.setComponent(content);
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public Panel getContent() {
		return content;
	}

	public void setContent(Panel content) {
		this.content = content;
	}

	public List<LBJComponent> getComponents() {
		if (components == null) {
			components = new ArrayList<>();
		}
		return components;
	}

	public boolean addComponent(LBJComponent component) {
		return getComponents().add(component);
	}

	public List<LBJFormUpdater<LBJForm>> getUpdaters() {
		if (updaters == null) {
			updaters = new ArrayList<>();
		}
		return updaters;
	}

	public boolean addUpdater(LBJFormUpdater<? extends LBJForm> updater) {
		return getUpdaters().add((LBJFormUpdater<LBJForm>) updater);
	}

	public List<LBJFormValidator<LBJForm>> getValidators() {
		if (validators == null) {
			validators = new ArrayList<>();
		}
		return validators;
	}

	public boolean addValidator(LBJFormValidator<? extends LBJForm> validator) {
		return getValidators().add((LBJFormValidator<LBJForm>) validator);
	}

}
