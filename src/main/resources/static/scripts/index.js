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
        if (xhr.readyState === 4 && xhr.status === 404) {
            $("#login-button").notify("Please sign up first.")
        }
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#login-button").notify("Book added successfully", "success");
            let obj = JSON.parse(xhr.responseText);
            localStorage.setItem("user", obj._links.self.href);
        }
    };

    xhr.open('GET', '/api/data/users/search/findByUsername?username=' + user.username);
    xhr.send();
}

