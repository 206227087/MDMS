package com.chaos.medical.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

public class TestActiviti {

    @Test
    public void deploy() {
        //得到流程引擎的方式三，利用底层封装，来加载配置文件，只需要调用方法即可
        ProcessEngine pec = ProcessEngines.getDefaultProcessEngine();

        //部署的服务对象
        RepositoryService repositoryService = pec.getRepositoryService();

        //部署请假任务
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/leave.bpmn")
                .name("请假")
                .deploy();

        System.out.println("请假部署ID：" + deploy.getId());
    }


    /**
     * 开始任务
     */
    @Test
    public void start() {
        //根据流程引擎对象得到运行时服务对象
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        //根据key得到流程实例
        ProcessInstance myProcess1 = runtimeService.startProcessInstanceByKey("myProcess_1");
        //输出运行时流程实例id以及我们启动的流程它的一个定义id
        System.out.println("流程实例id：" + myProcess1.getId());
        System.out.println("流程定义ID:" + myProcess1.getProcessDefinitionId());
    }


    /**
     * 完成任务
     */
    @Test
    public void complete() {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
        //根据ID完成任务
        taskService.complete("5002");
    }
}
