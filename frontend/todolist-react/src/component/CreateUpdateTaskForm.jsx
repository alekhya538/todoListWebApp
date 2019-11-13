import React, { Component } from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import TaskService from '../service/TaskService';

/**
 * @author Alekhya Ejjina
 * @date 13/11/2019
 */
class CreateUpdateTaskForm extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            description: '',
			status: '',
			message: null
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
    }

    componentDidMount() {

        console.log(this.state.id)

        // eslint-disable-next-line
        if (this.state.id == -1) {
             return
        }

        TaskService.getTask(this.state.id)
            .then(response => this.setState({
                description: response.data.description
            }))
    }

    validate(values) {
        let errors = {}	 
		
		/*TaskService.getTaskByDesc(values.description)
			.then(response => this.setState({
                description: response.data.description
            }))
			
		 let task = {
            id: this.state.id,
            description: this.state.description
        }*/
		
        if (!values.description) {
            errors.message = 'Enter a Description'
        } else if(values.description.length > 240) {
			errors.message = 'Task Description should not be more than 240 characters'
		}
        return errors
    }

    onSubmit(values) {
		console.log("onSubmit");
        let task = {
            id: this.state.id,
            description: values.description
        }

        if (this.state.id === "-1") {
			console.log("create");
            TaskService.createTask(task).then(() => this.props.history.push('/tasks'))
        } else {
			console.log("update");
            TaskService.updateTask(this.state.id, task).then(() => this.props.history.push('/tasks'))
        }

        console.log(values);
    }

    render() {

        let { description, status, id } = this.state

        return (
            <div>
                <h3>Task</h3>
                <div className="container">
                    <Formik
                        initialValues={{ id, description, status }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="message" component="div" className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Description</label>
                                        <Field className="form-control" type="text" name="description" />
                                    </fieldset>
                                    <button className="btn btn-success" type="submit">Save</button>
                                </Form>
                            )
                        }
                    </Formik>

                </div>
            </div>
        )
    }
}

export default CreateUpdateTaskForm