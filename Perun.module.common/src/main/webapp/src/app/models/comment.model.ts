import DateTimeFormat = Intl.DateTimeFormat;
import DateTimeFormat = Intl.DateTimeFormat;

export class Comment {

  commentId: string;
  commentAuthor: string;
  commentDate: DateTimeFormat;
  commentModify: boolean;
  commentModifyDate: DateTimeFormat;
  commentContent: string;
}
