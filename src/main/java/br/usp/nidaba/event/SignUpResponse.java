package br.usp.nidaba.event;

import java.io.Serializable;

public class SignUpResponse implements Serializable{
	private static final long serialVersionUID = 7953875739030655148L;
	
	private SignUpStatus signUpStatus;

	public SignUpResponse(SignUpStatus signUpStatus) {
		this.signUpStatus = signUpStatus;
	}

	public SignUpStatus getSignUpStatus() {
		return signUpStatus;
	}

	public void setSignUpStatus(SignUpStatus signUpStatus) {
		this.signUpStatus = signUpStatus;
	}
	
}
