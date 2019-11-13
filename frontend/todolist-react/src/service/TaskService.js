import axios from 'axios'

/**
 * @author Alekhya Ejjina
 * @date 13/11/2019
 */
const TASKS_API_URL = 'http://localhost:8080'

class TaskService {

    getAllTasks() {
        console.log('executing getAllTasks service..')
        return axios.get(`${TASKS_API_URL}/tasks`);
    }

    getTask(id) {
        console.log('executing getTask service')
        return axios.get(`${TASKS_API_URL}/tasks/${id}`);
    }
	
	getTaskByDesc(description) {
        console.log('executing getTaskByDesc service')
        return axios.get(`${TASKS_API_URL}/taskByDesc/${description}`);
    }

    deleteTask(id) {
        console.log('executing deleteTask service')
        return axios.delete(`${TASKS_API_URL}/tasks/${id}`);
    }
	
	completeTask(id) {
        console.log('executing completeTask service ' + id)
        return axios.put(`${TASKS_API_URL}/completeTask/${id}`);
    }

    updateTask(id, task) {
        console.log('executing updateTask service')
        return axios.put(`${TASKS_API_URL}/tasks/${id}`, task);
    }

    createTask(task) {
        console.log('executing createTask service')
        return axios.post(`${TASKS_API_URL}/tasks/`, task);
    }
}

export default new TaskService()