// /* eslint-disable*/
import { get, post, deletes } from './http'

// =======================> 管理员 API
// 是否登录成功
export const getLoginStatus = (params) => post(`login/login?userName=${params.get("userName")}&passWord=${params.get("passWord")}`)

// =======================> 用户 API
// 返回所有用户
export const getAllUser = () => get(`user/all`)
// 返回指定ID的用户
export const getUserOfId = (id) => get(`user/detail?id=${id}`)
// 添加用户
export const setUser = (params) => post(`user/add`, params)
// 更新用户信息
export const updateUserMsg = (params) => post(`user/update`, params)
// 删除用户
export const deleteUser = (id) => post(`user/delete?id=${id}`)


// =======================> 收藏列表 API
// 返回的指定用户ID收藏列表
export const getCollectionOfUser = (userId) => get(`collection/detail?userId=${userId}`)
// 删除收藏的歌曲
export const deleteCollection = (userId, songId) => get(`/collection/delete?userId=${userId}&&songId=${songId}`)

// =======================> 评论列表 API
// 获得指定歌曲ID的评论列表
export const getCommentOfSongId = (songId) => get(`comment/song/detail?songId=${songId}`)
// 获得指定歌单ID的评论列表
export const getCommentOfSongListId = (songListId) => get(`comment/songList/detail?songListId=${songListId}`)
// 更新评论
export const updateCommentMsg = (params) => post(`comment/update`, params)
// 删除评论
export const deleteComment = (id) => post(`comment/delete?id=${id}`)


// =======================> 歌手 API
// 返回所有歌手
export const getAllSinger = () => post(`singer/q/all`)
// 添加歌手
export const setSinger = (params) => post(`singer/add`, params)
// 更新歌手信息
export const updateSingerMsg = (params) => post(`singer/update`, params)
// 删除歌手
// export const deleteSinger = (id) => post(`singer/del`, id)
export const deleteSinger = (id) => post(`singer/del?id=${id}`)


// =======================> 歌曲 API
// 返回所有歌曲
export const getAllSong = () => get(`song/all`)
// 返回指定歌手ID的歌曲
export const getSongOfSingerId = (id) => get(`song/singer/detail?singerId=${id}`)
// 返回的指定用户ID收藏列表
export const getSongOfId = (id) => get(`song/detail?id=${id}`)
// 返回指定歌手名的歌曲
export const getSongOfSingerName = (id) => get(`song/singerName/detail?name=${id}`)
// 更新歌曲信息
export const updateSongMsg = (params) => post(`song/update`, params)
// 删除歌曲 singerId=${singerId}&name=${singerName}
// export const deleteSong = (singerId,singerName) => post(`song/delete`,singerId,singerName)
export const deleteSong = (singerId,idx) => post(`song/delete/${singerId}/${idx}`)


// =======================> 歌单 API
// 添加歌单
export const setSongList = (params) => post(`songList/add`, params)
// 获取全部歌单
export const getSongList = () => get(`songList/all`)
// 更新歌单信息
export const updateSongListMsg = (params) => post(`songList/update`, params)
// 删除歌单
export const deleteSongList = (id) => post(`songList/delete/${id}`)


// =======================> 歌单歌曲 API
// 给歌单添加歌曲
export const setListSong = (params) => post(`listSong/add`, params)
// 返回歌单里指定歌单ID的歌曲
export const getListSongOfSongId = (songListId) => get(`listSong/detail?songListId=${songListId}`)
// 删除歌单里的歌曲
export const deleteListSong = (songId) => post(`listSong/delete?id=${songId}`)
