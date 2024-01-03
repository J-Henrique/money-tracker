package com.jhbb.tracker_domain.use_case.synchronize_register

import com.jhbb.core_domain.model.Register

class SynchronizationException(val item: Register): Throwable()