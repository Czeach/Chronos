package com.czech.chronos.utils

class Queue<T> (list:MutableList<T>){

    var items: MutableList<T> = list

    fun isEmpty():Boolean = items.isEmpty()

    override fun toString() = items.toString()

    fun add(element: T){
        items.add(element)
    }

    @Throws(Exception::class)
    fun remove(): T {
        if (this.isEmpty()){
            throw Exception("fun 'remove' threw an exception: Nothing to remove from the queue.")
        } else return items.removeAt(0)
    }

    fun peek():T?{
        return if(this.isEmpty()) null
        else items[0]
    }

}