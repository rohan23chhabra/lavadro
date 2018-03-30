function addBook() {
    let book = {
        name: document.getElementById('name-input-book').value,
        price: parseInt(document.getElementById('price-input-book').value),
        author: document.getElementById('author-input-book').value,
        pages: document.getElementById('pages-input-book').value,
        subject: document.getElementById('subject-input-book').value,
        user: localStorage.getItem('user')
    }

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.status === 201 && xhr.readyState === 4) {
            $.notify('Book added successfully', {position: 'center'});
        }
    }

    xhr.open('POST', "/api/data/books");
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(book));
}

function addCycle() {
    let cycle = {
        name: document.getElementById('name-input-book').value,
        price: parseInt(document.getElementById('price-input-book').value),
        model: document.getElementById('model-input-cycle').value,
        company: document.getElementById('company-input-cycle').value,
        user: localStorage.getItem('user')
    }

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.status === 201 && xhr.readyState === 4) {
            $.notify('Cycle added successfully', {position: 'center'});
        }
    }

    xhr.open('POST', "/api/data/cycles");
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(cycle));
}
