package com.example.domain

import java.io.Closeable

interface ClosableJob : Closeable, Runnable
