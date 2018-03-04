interface HttpClient {
    baseURI: string;

    get(request: HttpRequest, callback);

    post(request: HttpRequest, callback);

}

class HttpEntity {
    public headers: object;
    public body: object;

    constructor(headers: object, body: object) {
        this.headers = headers;
        this.body = body;
    }
}

class HttpRequest extends HttpEntity {
    public method: string;
    public url: URL;

    constructor(headers: object, body: object, method: string, url: URL) {
        super(headers, body);
        this.method = method;
        this.url = url;
    }
}

class HttpResponse extends HttpEntity {
    public statusCode: number;

    constructor(headers: object, body: object, statusCode: number) {
        super(headers, body);
        this.statusCode = statusCode;
    }
}

class XhrHttpClient implements HttpClient {
    baseURI: string;

    get(request: HttpRequest, callback) {

    }

    post(request: HttpRequest, callback) {

    }

}

