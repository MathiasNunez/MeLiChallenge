package com.mnunez.core.permissions

/**
 *
 * Implement this interface wherever you need to listen on a requested permission result
 *
 */
interface PermissionsCallback {

    /**
     *
     * All permissions where granted
     *
     */
    fun granted()

    /**
     *
     * There where some denied permissions, amount of is:
     * @param denied
     *
     */
    fun denied(denied: Int)

    /**
     *
     * If need to request rationale, this method will be called
     *
     * @param permission a list of permissions that can use a rationale request
     *
     */
    fun requestRationale(permission: MutableList<String>)

}