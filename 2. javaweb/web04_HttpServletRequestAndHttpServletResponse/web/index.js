const formEl = document.querySelector(".form");
const usernameEl = document.querySelector('[type=text]');
const passwordEl = document.querySelector('[type=password]')

formEl.addEventListener('submit', async e => {
    e.preventDefault();
    const username = usernameEl.value;
    const password = passwordEl.value;
    const res = await fetch(`http://localhost:8080/login?username=${username}&password=${password}`);
    console.log(res);
})