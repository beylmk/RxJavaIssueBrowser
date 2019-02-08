/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package maddie.practice.com.rxjavaissuebrowser

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import maddie.practice.com.rxjavaissuebrowser.model.GithubIssueDatabase
import maddie.practice.com.rxjavaissuebrowser.model.issue.Issue
import maddie.practice.com.rxjavaissuebrowser.model.issue.IssueDao
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 */
@RunWith(AndroidJUnit4::class)
class IssueDaoTest {

    companion object {

        val issue1 = Issue(0L, 123, "Whistle needs an Android engineer", "Whistle should hire Maddie!!", "2019-02-06T14:39:23Z")
        val issue2 = Issue(1L, 1234, "there are so many dogs in the world", "we should adopt ALL the dogs", "2019-02-07T14:39:23Z")
        val issue3 = Issue(2L, 1234, "dogs are adventurous", "...which is why we love them, but we should get whistles to keep track " +
            "of them!", "2019-02-07T14:39:24Z")
        val extraIssue = Issue(3L, 121, "cats can be lazy", "tracking their activity can help us to make an action plan for their " +
            "health", "2019-02-07T14:39:58Z")
        val duplicateIdIssue = Issue(1L, 111, "sometimes an api may give an object with a duplicate id", "when this happens we should " +
            "overwrite the old id", "2019-01-07T14:39:58Z")
        val issueList = listOf(issue1, issue2, issue3)
    }

    lateinit var mDatabase: GithubIssueDatabase
    lateinit var issueDao: IssueDao

    @Before
    @Throws(Exception::class)
    fun initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            GithubIssueDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        issueDao = mDatabase.githubIssueDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        mDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun getRxJavaIssuesBeforeInsertingReturnsEmpty() {
        val issues = LiveDataTestUtil.getValue(issueDao.getRxJavaIssues())
        assertTrue(issues.isEmpty())
    }

    @Test
    @Throws(InterruptedException::class)
    fun getRxJavaIssuesOrder() {
        issueDao.insertAll(issueList)

        val testIssues = LiveDataTestUtil.getValue(issueDao.getRxJavaIssues())
        assertThat(testIssues.first().updatedTimestamp, `is`(issue3.updatedTimestamp))
        assertThat(testIssues.last().updatedTimestamp, `is`(issue1.updatedTimestamp))
    }

    @Test
    @Throws(InterruptedException::class)
    fun getRxJavaIssuesAfterInserting() {
        issueDao.insertAll(issueList)

        val testIssues = LiveDataTestUtil.getValue(issueDao.getRxJavaIssues())
        assert(testIssues.containsAll(issueList))
    }

    @Test
    @Throws(InterruptedException::class)
    fun getIssueById() {
        issueDao.insertAll(issueList)

        val testIssue = LiveDataTestUtil.getValue(issueDao.getIssueById(issue1.id))
        assertEquals(issue1, testIssue)
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertNewIssueAddsToExisting() {
        issueDao.insertAll(issueList)
        issueDao.insertAll(listOf(extraIssue))

        val testIssues = LiveDataTestUtil.getValue(issueDao.getRxJavaIssues())
        assertEquals(issueList.size + 1, testIssues.size)
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertIssuesReplacesOnConflict() {
        issueDao.insertAll(issueList)
        issueDao.insertAll(listOf(duplicateIdIssue))

        val testIssue = LiveDataTestUtil.getValue(issueDao.getIssueById(duplicateIdIssue.id))
        assertEquals(duplicateIdIssue, testIssue)
    }
}
