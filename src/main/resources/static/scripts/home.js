function addBook() {
    let book = {
        name : document.getElementById('name-input-book').value,
        price : parseInt(document.getElementById('price-input-book').value),
        author : document.getElementById('author-input-book').value,
        pages : document.getElementById('pages-input-book').value,
        subject : document.getElementById('subject-input-book').value,
        user : JSON.parse(localStorage.getItem('user'))
    }

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if(xhr.status === 201 && xhr.readyState === 4)
        {
            $.notify('Book added successfully', {position : 'center'});
        }
    }

    xhr.open('POST', "/api/data/products");
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(book));
}