package com.ceilfors.jenkins.plugins.jirabuilder

import com.atlassian.jira.rest.client.api.domain.Comment
import com.ceilfors.jenkins.plugins.jirabuilder.jira.JiraClient
import com.ceilfors.jenkins.plugins.jirabuilder.jira.JiraUtils
import hudson.model.AbstractProject

import javax.inject.Inject

/**
 * @author ceilfors
 */
class CommentingJiraBuilderListener implements JiraBuilderListener {

    @Inject
    JiraClient jiraClient

    @Override
    void buildScheduled(Comment comment, Collection<? extends AbstractProject> projects) {
        def issueId = JiraUtils.getIssueIdFromComment(comment)
        jiraClient.addComment(issueId, "Build is scheduled for: " + projects.collect { it.absoluteUrl} )
    }

    @Override
    void buildNotScheduled(Comment comment) {
    }
}