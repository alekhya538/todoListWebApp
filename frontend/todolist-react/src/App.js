import React, { Component } from 'react';
import './App.css';
import TaskApp from './component/TaskApp';

class App extends Component {
  render() {
    return (
      <div className="container">
        <TaskApp />
      </div>
    );
  }
}

export default App;
