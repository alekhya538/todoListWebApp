import React, { Component } from 'react';
import ListTasks from './ListTasks';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import CreateUpdateTaskForm from './CreateUpdateTaskForm';

/**
 * @author Alekhya Ejjina
 * @date 13/11/2019
 */
class TaskApp extends Component {
    render() {
        return (
            <Router>
                <>
                    <Switch>
                        <Route path="/" exact component={ListTasks} />
                        <Route path="/tasks" exact component={ListTasks} />
                        <Route path="/tasks/:id" component={CreateUpdateTaskForm} />
                    </Switch>
					<br/><br/><br/>
					<p align="center">@2019 Copy Rights Alekhya Ejjina</p>
                </>
            </Router>
        )
    }
}

export default TaskApp