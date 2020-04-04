package gui.forms.mainmenu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextGUIThread;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.MouseCaptureMode;
import com.googlecode.lanterna.terminal.Terminal;

import constants.Labels;
import constants.Settings;
import gui.builders.LBJPlainLabelBuilder;
import gui.components.LBJPlainLabel;
import gui.forms.LBJForm;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.preferences.PreferencesForm;
import gui.forms.removenotnullconstraint.RemoveNotNullConstraintForm;
import gui.suppliers.LBJFormSupplier;
import gui.utils.LBJFormUtils;

/**
 * <p>
 * Main menu for this application. Main purpose of this form other than getting
 * to another form is to update other forms as this is where {@link Terminal} is
 * running is updating all other forms on any application event such as user
 * input, resize or move. To be able to update all forms it has to have their
 * reference in {@link #formsToUpdate} see {@link #updateForms()}.
 * <p>
 * Other small difference of this form is that it does not have any
 * {@link Button}s as it has all "links" to other forms stored in
 * {@link ActionListBox} as {@link Runnable}s ({@link LBJForm} implements
 * {@link Runnable}). Because of that mouse support is currently not available
 * on this form (it looks better tho).
 */
public class MainMenuForm extends LBJForm {

	private static final Logger LOGGER = Logger.getLogger(MainMenuForm.class.getSimpleName());

	private boolean terminalStarted;
	private List<LBJForm> formsToUpdate;
	private LBJPlainLabel questionLabel;
	private ActionListBox mainMenu;
	private CreateTableForm createTableForm;
	private AddColumnForm addColumnForm;
	private RemoveNotNullConstraintForm removeNotNullConstraintForm;
	private PreferencesForm preferencesForm;
	private TextGUIThread thread;

	public MainMenuForm() {
		this(new BasicWindow(Labels.WINDOW_NAME));
	}

	private MainMenuForm(Window window) {
		super(window);
		initialize();
	}

	@Override
	public String toString() {
		return Labels.MAIN_MENU_FORM;
	}

	@Override
	public void initializeComponents() {
		mainMenu = new ActionListBox();
		questionLabel = new LBJPlainLabelBuilder(Labels.MAIN_MENU_QUESTION, this).build();
		createTableForm = LBJFormSupplier.getCreateTableForm(getWindow(), this, false);
		addColumnForm = LBJFormSupplier.getAddColumnForm(getWindow(), this, false);
		removeNotNullConstraintForm = LBJFormSupplier.getRemoveNotNullConstraintForm(getWindow(), this, false);
		preferencesForm = LBJFormSupplier.getPreferencesForm(getWindow(), this, false);

		addFormToUpdate(createTableForm);
		addFormToUpdate(addColumnForm);
		addFormToUpdate(removeNotNullConstraintForm);
		addFormToUpdate(preferencesForm);
	}

	@Override
	public void initializeButtons() {
		// no buttons in main menu, everything is in ActionListBox - mainMenu
	}

	@Override
	public void addComponents() {
		LBJFormUtils.addLabelToMainMenuContent(getContent(), questionLabel);
		LBJFormUtils.addMenuToMainMenuContent(getContent(), mainMenu);
		LBJFormUtils.addItemToMenu(mainMenu, createTableForm, Labels.MAIN_MENU_CREATE_TABLE);
		LBJFormUtils.addItemToMenu(mainMenu, addColumnForm, Labels.MAIN_MENU_ADD_COLUMN);
		LBJFormUtils.addItemToMenu(mainMenu, removeNotNullConstraintForm, Labels.MAIN_MENU_REMOVE_NOT_NULL_CONSTRAINT);
		LBJFormUtils.addItemToMenu(mainMenu, preferencesForm, Labels.MAIN_MENU_PREFERENCES);
		LBJFormUtils.addExitButtonToMainMenu(mainMenu);
	}

	@Override
	public void addButtons() {
		// no buttons in main menu, everything is in ActionListBox - mainMenu
	}

	@Override
	public void addFormUpdaters() {
		// no updaters
	}

	@Override
	public void addFormValidators() {
		// no validators
	}

	@Override
	public void initializeContent() {
		setContent(new Panel(new GridLayout(1)));
	}

	public void startTerminalAndFocus() {
		focus();
		startTerminal();
	}

	public void startTerminal() {
		try (Screen screen = new DefaultTerminalFactory().setMouseCaptureMode(MouseCaptureMode.CLICK)
				.setInitialTerminalSize(Settings.TERMINAL_SIZE).createScreen()) {
			screen.startScreen();
			WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);
			thread = gui.getGUIThread();
			getWindow().addWindowListener(new WindowListener() {

				@Override
				public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {
					updateForms();
				}

				@Override
				public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {
					updateForms();
				}

				@Override
				public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {
					updateForms();
				}

				@Override
				public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {
					updateForms();
				}
			});
			gui.addWindowAndWait(getWindow());
		} catch (Exception e) {
			LOGGER.severe("Exception has occurred while running terminal");
			throw new IllegalStateException(e);
		}
	}

	public void updateForms() {
		for (LBJForm lbjForm : getFormsToUpdate()) {
			lbjForm.update();
		}
	}

	/**
	 * Child forms that are going to be updated each time user does some action.
	 * Only currently visible form will be updated.
	 */
	public List<LBJForm> getFormsToUpdate() {
		if (formsToUpdate == null) {
			formsToUpdate = new ArrayList<>();
		}
		return formsToUpdate;
	}

	public boolean addFormToUpdate(LBJForm form) {
		return getFormsToUpdate().add(form);
	}

	public boolean isTerminalStarted() {
		return terminalStarted;
	}

	public LBJPlainLabel getQuestionLabel() {
		return questionLabel;
	}

	public ActionListBox getMainMenu() {
		return mainMenu;
	}

	public CreateTableForm getCreateTableForm() {
		return createTableForm;
	}

	public AddColumnForm getAddColumnForm() {
		return addColumnForm;
	}

	public RemoveNotNullConstraintForm getRemoveNotNullConstraintForm() {
		return removeNotNullConstraintForm;
	}

	public TextGUIThread getThread() {
		return thread;
	}

}
