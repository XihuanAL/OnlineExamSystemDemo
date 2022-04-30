import {createRouter, createWebHistory} from 'vue-router'

const routes = [
    {
        path: '/dashboard',
        component: () => import('../views/Dashboard.vue'),
        redirect:"/subject",
        children: [
            {
                path: '/exam',

                component: () => import('../views/management/ExamManagementView.vue')
            },
            {
                path: '/question',
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
            {
                path: '/exam/add',
                component: () => import('../views/exampaper/ExamPaperAdd')
            },
            {
                path: '/exam/edit',
                name: "examEdit",
                component: () => import('../views/exampaper/ExamPaperEdit')
            }
        ]
    },
    {
        path: '/login',
        component: () => import('../views/Login.vue'),
    },
    {
        path: '/',
        redirect: '/login'
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
