var trainnerId = null;

function belongsToGroup(user, groupName) {
	var belongs = false;
	if (user.groups !== undefined) {
		for (var i = 0; i < user.groups.length; i++) {
			if (user.groups[i].name == groupName) {
				return true;
			}
		}
	}
	return belongs;
}