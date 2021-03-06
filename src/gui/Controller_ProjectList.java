package gui;

import model.MyDate;
import model.Project;
import  model.TeamMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class Controller_ProjectList extends Controller {

    public Label roleSelectedLabel;
    @FXML protected Button backButton;
	@FXML protected Button createButton;
	@FXML protected Button addEmployeeButton;
	@FXML protected Button exportButton;

	@FXML private TableView<Project> projectTableView;
	@FXML private TableColumn<Project, String> projectName;
	@FXML private TableColumn<Project, MyDate> startDate;
	@FXML private TableColumn<Project, MyDate> deadLine;
	@FXML private TableColumn<Project, String> projectStatus;

	@FXML private TableView<TeamMember> employeeTableView;
	@FXML private TableColumn<TeamMember, String> employeeNameColumn;
	@FXML private TableColumn<TeamMember, Integer> idNumberColumn;
	@FXML private TableColumn<TeamMember, MyDate> birthdayColumn;

	public Controller_ProjectList( ) {
	}

	@Override
	public void init( ) {
		populateProjectTable();
		populateEmployeeTable();

		buttonLogic( );

		roleSelectedLabel.setText(ColourItGui.getModel().getUseRoleString());
	}

	// general button logic
	private void buttonLogic( ) {
		// if the role is not admin check what buttons should be removed. else skip.
		if (ColourItGui.getModel().getUserRole() != 4) {
			// If the role is not project creator hide create button
			if (ColourItGui.getModel().getUserRole() != 3)
				createButton.setVisible(false);
		}
	}

	// functions for populating data one scene
	private void populateProjectTable(){

		ObservableList<Project> observableProjects = FXCollections.observableArrayList( );
		observableProjects.addAll(ColourItGui.getModel( ).getProjectList( ).getProjects( ));

		projectName.setCellValueFactory(new PropertyValueFactory<>("name"));
		startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		deadLine.setCellValueFactory(new PropertyValueFactory<>("deadLine"));
		projectStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

		projectTableView.setItems(observableProjects);
	}
	private void populateEmployeeTable(){

		ObservableList<TeamMember> observableTeamMembers = FXCollections.observableArrayList( );

		observableTeamMembers.addAll(ColourItGui.getModel( ).getTeamMemberList( ).getTeamMembers( ));

		employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		idNumberColumn.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
		birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));

		employeeTableView.setItems(observableTeamMembers);

		// disable selection functionality from employee tabel
		employeeTableView.setSelectionModel(null);

	}

	// functions for button/table functionality
	public void exportToWebsite( ) {
		// TODO: Skal eksportere til vores hjemmeside via XML.
	}

	@Override
	public void goBack( ) {
		init();
	}


	public void itemSelected( ) {  //SANDER DON'T FUCKING REMOVE THIS PLEASE

		try {
			Project selectedProject = projectTableView.getSelectionModel( ).getSelectedItem( );
			ColourItGui.getModel().setSelectedProject(selectedProject);
			ColourItGui.setRoot("requirementList");
		} catch ( Exception e ) {
			//System.out.println("No project selected");
		}
	}

	public void addEmployee( ) throws IOException {
		createPopUp("popUp_TeamMemberList_Add");
	}

	public void createProjectButton( ) throws IOException {
		createPopUp("popUp_Project_Create");
	}

}