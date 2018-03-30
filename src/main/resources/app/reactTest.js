import React from 'react';
import ReactDOM from 'react-dom';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'

class Test extends React.Component {
    render() {
        return (<MuiThemeProvider>
            <div>
                Hello React.
            </div>
        </MuiThemeProvider>);

    }
}

function render() {
    ReactDOM.render(<Test/>, document.getElementById('root'));
}

