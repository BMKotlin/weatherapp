package com.viht.weathermvvm

import com.viht.domain.utils.DateUtil
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DateTest {
    @Test
    fun testGetDate() {
        val timeStamp = 1653192000L
        val date = DateUtil.getDate(timeStamp)
//        println(date)
        assertNotNull(date)
        assertTrue(date.contains("Sunday"))
        assertTrue(date.contains("May"))
        assertEquals("Sunday, 22 May 2022", date)
    }
}