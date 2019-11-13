import React, { Component } from 'react'
import TaskService from '../service/TaskService';

/**
 * @author Alekhya Ejjina
 * @date 13/11/2019
 */
class ListTasks extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tasks: [],
            message: null
        }
        this.deleteTaskClicked = this.deleteTaskClicked.bind(this)
        this.updateTaskClicked = this.updateTaskClicked.bind(this)
		this.completeTaskClicked = this.completeTaskClicked.bind(this)
        this.addTaskClicked = this.addTaskClicked.bind(this)
        this.refreshTasks = this.refreshTasks.bind(this)
    }

    componentDidMount() {
        this.refreshTasks();
    }

    refreshTasks() {
        TaskService.getAllTasks()
            .then(
                response => {
                    console.log(response);
                    this.setState({ tasks: response.data })
                }
            )
    }

    deleteTaskClicked(id) {
        TaskService.deleteTask(id)
            .then(
                response => {
                    this.setState({ message: `Delete of Task ${id} Successful` })
                    this.refreshTasks()
                }
            )
    }

    addTaskClicked() {
		console.log('addTaskClicked()');		
        this.props.history.push('/tasks/-1')
		this.setState({ message: `Task added` })
    }

    updateTaskClicked(id) {
        console.log('updateTaskClicked() - ' + id)		
        this.props.history.push('/tasks/'+id)
		this.setState({ message: `Task ${id} updated` })
    }
	
	completeTaskClicked(id) {
        TaskService.completeTask(id)
            .then(
                response => {
                    this.setState({ message: `Task ${id} marked Complete` })
                    this.refreshTasks()
                }
            )
    }

    render() {
        console.log('render')
        return (
            <div className="container">
				<br/><p align="right">ToDo List Web Application by <b><i>Alekhya Ejjina</i></b></p>
                <h3><u>ToDo</u></h3><br/>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>                                
                                <th colSpan="4">Task Description</th>                                
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.tasks.map(
                                    task => {
										if(task.status === "todo")
                                        return <tr key={task.id}>                                            
                                            <td>{task.description}</td>
											<td><button className="btn btn-success" onClick={() => this.updateTaskClicked(task.id)}>Update</button></td>
                                            <td><button className="btn btn-success" onClick={() => this.completeTaskClicked(task.id)}>Mark Complete</button></td>
                                            <td><button className="btn btn-warning" onClick={() => this.deleteTaskClicked(task.id)}>Delete</button></td>
                                        </tr>
									}
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <button className="btn btn-success" onClick={this.addTaskClicked}>Add</button>
                    </div>
                </div>
				
				<br/><br/><br/>
				<h3><u>Completed</u></h3><br/>
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th colSpan="3"	>Task Description</th>                             
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.tasks.map(
                                    task => {
										if(task.status === "complete")
                                        return <tr key={task.id}>                                            
                                            <td>{task.description}</td>                                            
                                            <td><button className="btn btn-warning" onClick={() => this.deleteTaskClicked(task.id)}>Delete</button></td>
                                        </tr>										
									}
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ListTasks