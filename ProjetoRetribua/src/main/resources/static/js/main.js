const signUpButton = document.getElementById('signUp');
const signUpButtonMobile = document.getElementById('signUpMobile');

const signInButton = document.getElementById('signIn');
const signInButtonMobile = document.getElementById('signInMobile');
const container = document.getElementById('container');


signUpButtonMobile.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});
signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});
signInButtonMobile.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

/*
let btn = document.querySelector('.olho');
btn.addEventListener('click', function() {
    let input = document.querySelector('#password');
    if(input.getAttribute('type') == 'password') {
        input.setAttribute('type', 'text');
    } else {
        input.setAttribute('type', 'password');
    }
});*/

