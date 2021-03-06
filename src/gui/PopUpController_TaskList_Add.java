package gui;

import model.Task;
import model.TeamMember;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PopUpController_TaskList_Add extends Controller {

	public Button confirmButton;
	public CheckBox highPriorityCheckBox;
	public TextField taskNameTextField;
	public TextField estimatedHoursTextField;
	public TextArea taskDescription;
	public Button cancelButton;
	public ChoiceBox<TeamMember> responsibleTeamMember;

	@Override
	public void init() {
		responsibleTeamMember.getItems().addAll(ColourItGui.getModel().getSelectedProject().getTeamMemberList().getTeamMembers());
	}

	@FXML
	private void addTask() {
		String name = taskNameTextField.getText();
		int time = Integer.parseInt(estimatedHoursTextField.getText());
		String memberName = responsibleTeamMember.getSelectionModel().getSelectedItem().getName();
		boolean priority = highPriorityCheckBox.isSelected();
		String taskDesc = taskDescription.getText();

		// getting the task list from selected requirement, adding a new task to the list
		ColourItGui.getModel().getSelectedRequirement()
				.getTaskList().addTask(new Task(name, time, memberName, priority, taskDesc));

		goBack();
	}

	@Override
	public void goBack() {
		getParentController().init();
		((Stage) confirmButton.getScene().getWindow()).close(); // Get's the Window the button is in, and casts to a Stage, which can be closed with .close()
	}
}
