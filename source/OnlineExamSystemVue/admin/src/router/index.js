import {createRouter, createWebHistory} from 'vue-router'

const routes = [
    {
        path: '/exam',
        component: () => import('../views/management/ExamManagementView.vue')
    },
    {
        path: '/',
        component: () => import('../views/management/QuestionManagementView.vue')
    },
    {
        path: '/subject',
        component: () => import('../views/management/SubjectManagementView.vue')
    },
    {
        path: '/student',
        component: () => import('../views/management/StudentManagementView.vue')
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
