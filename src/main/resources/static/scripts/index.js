function onSignUpButtonClick() {
    let usernameValue = document.getElementById('username-input').value;
    let passwordValue = document.getElementById('password-input').value;
    let user = {
        'username': usernameValue,
        'password': passwordValue
    };

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 201) {
            $('#signup-button').notify("User successfully signed up!", "success");
        }
    }

    xhr.open("POST", "/api/data/users");
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(user));
}

function onLoginButtonClick() {
    let usernameValue = document.getElementById('username-input').value;
    let passwordValue = document.getElementById('password-input').value;
    let user = {
        'username': usernameValue,
        'password': passwordValue
    };

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let obj = JSON.parse(xhr.responseText);
            let flag = 0;
            let len = obj._embedded.users.length;
            for (let i = 0; i < len; i++) {
                if (obj._embedded.users[i].username === user.username && obj._embedded.users[i].password === user.password) {
                    $('#login-button').notify('User logged in successfully. ' +
                        'Click on top left corner to go to home page.', 'success');
                    localStorage.setItem('user', JSON.stringify(user));
                    flag = 1;
                    break;
                }
            }
            if (!flag) {
                $('#login-button').notify('Sign Up first', 'error');
            }
        }
    }

    xhr.open('GET', '/api/data/users');
    xhr.send();
}