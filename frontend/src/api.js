// src/api.js
import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8081',
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json; charset=utf-8'
    }
});

export default {
    getDisciplinas() {
        return apiClient.get('/disciplinas');
    }
};
