package ch.exmachina.vaadin.autoforms.showcase;

/**
 * @autor Marco Manzi
 */
public class TestModel {
	private String name = "OneFieldValue";
	private String surname = "OneFieldValue";
	private String desciption = "OneFieldValue";
	private String itwork = "OneFieldValue";
	public String multichoice = "FirstSelection";

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getItwork() {
		return itwork;
	}

	public void setItwork(String itwork) {
		this.itwork = itwork;
	}

	public String getMultichoice() {
		return multichoice;
	}

	public void setMultichoice(String multichoice) {
		this.multichoice = multichoice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}