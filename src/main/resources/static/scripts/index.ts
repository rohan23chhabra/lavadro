function onLoginButtonClick() {
    let user = {
        "username": document.getElementById("username-input").getAttribute("value"),
        "password": document.getElementById("password-input").getAttribute("value")
    };

    localStorage.setItem("user", JSON.stringify(user));

    let client: HttpClient = new XhrHttpClient();
    let request: HttpRequest = new HttpRequest({
        "Content-type": "application/json"
    }, user, "POST", new URL("/api/data/users"));


}