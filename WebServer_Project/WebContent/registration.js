const emailBox = document.getElementById('emailBox');
const usernameBox = document.getElementById('unameBox');
const passwordBox = document.getElementById('passwordBox');
const passwordRepeatBox = document.getElementById('passwordRepeatBox');
const submitButton = document.getElementById('submitButton');

submitButton.disabled = true;

const boxes = {
	email: {
		field: emailBox.querySelector('input'),
		errorMessage: emailBox.querySelector('.input-error-message'),
		valid: false
	},
	username: {
		field: usernameBox.querySelector('input'),
		errorMessage: usernameBox.querySelector('.input-error-message'),
		valid: false
	},
	password: {
		field: passwordBox.querySelector('input'),
		errorMessage: passwordBox.querySelector('.input-error-message'),
		valid: false
	},
	passwordCopy: {
		field: passwordRepeatBox.querySelector('input'),
		errorMessage: passwordRepeatBox.querySelector('.input-error-message'),
		valid: false
	}
}

function setValid(field, val, errorMessage = '', turnRed = true) {
	boxes[field].valid = val;

	if (turnRed || errorMessage === '') {
		boxes[field].errorMessage.innerHTML = errorMessage;
	}
	
	if (val) {
		boxes[field].field.classList.remove('invalid-input');
		checkAllFields();
	}
	else {
		if (turnRed) {
			boxes[field].field.classList.add('invalid-input');
		}
		submitButton.disabled = true;
	}
}

function checkAllFields() {
	let enable = true;
	for (let field in boxes) {
		if (!boxes[field].valid) {
			enable = false;
			break;
		}
	}
	if (enable) {
		submitButton.disabled = false;
	}
}



boxes.email.field.oninput = function(turnRed = true) {
	const val = boxes.email.field.value;
	let errorMessage = '';
	
	if (!checkEmail(val)) errorMessage = 'Please enter a valid email address.';
	else if (val.length > 64) errorMessage = 'Email cannot be more than 64 characters.';
	
	if (errorMessage) {
		setValid('email', false, errorMessage, turnRed);
	}
	else {
		setValid('email', true);
	}
}

boxes.username.field.oninput = function(turnRed = true) {
	const len = boxes.username.field.value.length;
	let errorMessage = '';
	
	if (len < 3) errorMessage = 'Username must be at least 3 characters';
	else if (len > 20) errorMessage = 'Username cannot be more than 20 characters.';
	
	if (errorMessage) {
		setValid('username', false, errorMessage, turnRed);
	}
	else {
		setValid('username', true);
	}
}

boxes.password.field.oninput = function(turnRed = true) {
	const len = boxes.password.field.value.length;
	let errorMessage = '';
	
	if (len < 8) errorMessage = 'Password must be at least 8 characters.';
	else if (len > 64) errorMessage = 'Password cannot be longer than 64 characters.';
	
	if (errorMessage) {
		setValid('password', false, errorMessage, turnRed);
	}
	else {
		setValid('password', true);
	}
	
	boxes.passwordCopy.field.oninput();
}

boxes.passwordCopy.field.oninput = function(turnRed = true) {
	if (boxes.password.field.value !== boxes.passwordCopy.field.value) {
		setValid('passwordCopy', false, "Passwords must match.", turnRed);
	}
	else {
		setValid('passwordCopy', true);
	}
}



function checkEmail(email) {
	const atLocation = email.indexOf('@');
	if (atLocation < 1) return false;
	if (email.indexOf('@', atLocation + 1) !== -1) return false;
	if (email.indexOf('.', atLocation + 1) === -1) return false;
	return true;
}


for (let box in boxes) {
	boxes[box].field.oninput(false);
}