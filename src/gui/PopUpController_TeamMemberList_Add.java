package gui;

import model.MyDate;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.DateTimeException;

public class PopUpController_TeamMemberList_Add extends Controller {
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField idNumberTextField;

	@FXML
	private DatePicker birthdateDatePicker;
	@FXML
	protected Label validationLabel;

	@Override
	public void init( ) {

        /*Editable false, to make sure user can't make invalid input. Like strings
		and invalid MyDate data.*/
		birthdateDatePicker.setEditable(false);
	}

	@FXML
	private void addTeamMember( ) {
		try {
			String teamMemberName = nameTextField.getText( );
			int employeeIdNumber = Integer.parseInt(idNumberTextField.getText( ));
			if ( MyDate.now( ).isBefore(new MyDate(birthdateDatePicker.getValue( ))) )
				throw new DateTimeException("Birthdate cannot be earlier than current date");
			MyDate birthday = new MyDate(birthdateDatePicker.getValue( ));

			ColourItGui.getModel( ).addEmployee(teamMemberName, employeeIdNumber, birthday);

			getParentController( ).init( );
		} catch ( DateTimeException dateTimeException ) {
			validationLabel.setText(dateTimeException.getMessage( ));
		}
	}

	@Override
	public void goBack( ) {
		getParentController( ).init( );
	}

}
